package com.user.config;
 
import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import com.user.model.UserDtls;
 
 
public class CustomUserDetails implements UserDetails {
 
	private UserDtls user;
	public CustomUserDetails(UserDtls user) {
		super();
		this.user = user;
	}
 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(simpleGrantedAuthority);
	}
 
	@Override
	public String getPassword() {
		return user.getPassword();
	}
 
	@Override
	public String getUsername() {
		return user.getEmail();
	}
 
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
 
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}