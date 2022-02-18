package net.codejava.controllers;

import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.models.ERole;
import net.codejava.models.Role;
import net.codejava.models.User;
import net.codejava.payload.request.LoginRequest;
import net.codejava.payload.request.SignupRequest;
import net.codejava.payload.request.UpdateNotesRequest;
import net.codejava.payload.response.JwtResponse;
import net.codejava.payload.response.MessageResponse;
import net.codejava.repository.RoleRepository;
import net.codejava.repository.UserRepository;
import net.codejava.security.jwt.JwtUtils;
import net.codejava.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * Sign in request handling
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getSurname(),
						userDetails.getUsername(), userDetails.getEmail(), userDetails.getNotes(), roles));
	}

	/**
	 * Update notes put request handling
	 * 
	 * @param updateNoteRequest
	 */
	@PutMapping("/updatenotes")
	public void updateUserNote(@Valid @RequestBody UpdateNotesRequest updateNoteRequest) {
		try {
			System.out.println("Note updated: " + updateNoteRequest.getUpdateNotes());
			User target = userRepository.findById(updateNoteRequest.getId()).get();
			target.setNotes(updateNoteRequest.getUpdateNotes());
			userRepository.save(target);

		} catch (NoSuchElementException e) {
		}
	}

	/**
	 * Sign up request handling
	 * 
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		// Use RegEx to check valid user information
		// first name check
		Pattern namePattern = Pattern.compile("^[A-Za-z ]+$");
		if (!namePattern.matcher(signUpRequest.getFirstName()).find()) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"It looks like you've entered a mobile number or email address. Please enter your name."));
		}

		if (signUpRequest.getFirstName().length() > 20) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Your first name's length should not exceed 20 charater."));
		}

		// surname check
		if (!namePattern.matcher(signUpRequest.getSurname()).find()) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"It looks like you've entered a mobile number or email address. Please enter your name."));
		}
		if (signUpRequest.getSurname().length() > 20) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Your surname name's length should not exceed 20 charater."));
		}

		// username check
		Pattern usernamePattern = Pattern.compile("^[a-z][a-z0-9_]+$");
		if (!usernamePattern.matcher(signUpRequest.getUsername()).find()) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Username must contains only lowercase words, numbers or '_'"));
		}

		if (signUpRequest.getUsername().length() < 5 || signUpRequest.getUsername().length() > 20) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username length must between 5 and 20. "));
		}

		// email check
		Pattern emailPattern = Pattern.compile("^[A-Za-z][A-Za-z0-9_]+@[a-z]{2,}(\\.[a-z]{2,}){1,4}$");
		if (!emailPattern.matcher(signUpRequest.getEmail()).find() || signUpRequest.getEmail().length() > 50) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please enter a valid email."));
		}

		// password check
		if (signUpRequest.getPassword().length() < 8 || signUpRequest.getPassword().length() > 40) {
			return ResponseEntity.badRequest().body(new MessageResponse("Password length must between 8 and 40. "));
		}

		// check if username or email is already taken.
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getSurname(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
