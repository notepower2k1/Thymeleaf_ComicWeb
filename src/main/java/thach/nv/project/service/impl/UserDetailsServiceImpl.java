package thach.nv.project.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thach.nv.project.entity.NguoiDung;
import thach.nv.project.repository.NguoiDungRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private NguoiDungRepository nguoidungRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung account = this.nguoidungRepo.findByTaikhoan(username);
		if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getTenVT()));
        
		return new org.springframework.security.core.userdetails.User(
				account.getTaikhoan(), account.getMatKhau(), grantedAuthorities);
	}

}
