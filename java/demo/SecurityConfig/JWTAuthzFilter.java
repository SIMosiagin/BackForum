package demo.SecurityConfig;

import io.jsonwebtoken.Jwts;
import demo.Service.CustomUserDetailService;
import demo.model.MyUser;
import demo.model.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static demo.SecurityConfig.SecConstant.HEADER_STRING;
import static demo.SecurityConfig.SecConstant.SECRET;
import static demo.SecurityConfig.SecConstant.TOKEN_PREFIX;

public class JWTAuthzFilter  extends BasicAuthenticationFilter{

    private final CustomUserDetailService customUserDetailService;

    public JWTAuthzFilter(AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authToken = getAuthToken(request);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;
        String name = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody()
                .getSubject();

        UserDetails userDetails = customUserDetailService.loadUserByUsername(name);
        MyUser user = customUserDetailService.loadMyUserByUsername(name);

        return name !=null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;

    }
}
