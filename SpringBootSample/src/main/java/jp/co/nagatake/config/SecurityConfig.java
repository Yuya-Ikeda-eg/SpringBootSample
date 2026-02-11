package jp.co.nagatake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	/**
	 * セキュリティ設定
	 * @param http
	 * @return HttpSecurity
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests(auth -> auth
				// 管理者専用
				.requestMatchers("/admin/**")
					.hasRole("ADMIN")
				// セキュリティ対象外 & 直打ちOK
				.requestMatchers(
					"/webjars/**", // セキュリティ対象外
					"/css/**", // セキュリティ対象外
					"/js/**", // セキュリティ対象外
					"/h2-console/**", // セキュリティ対象外
					"/login", // URL直打ちOK
					"/user/signup" // URL直打ちOK
				).permitAll()
				
				// それ以外は認証必須
				.anyRequest().authenticated()
			)
			.formLogin(form -> form 
				.loginPage("/login") // ログインページ
				.loginProcessingUrl("/login") // ログイン処理パス
				.usernameParameter("userId") // ユーザーID
				.passwordParameter("password") // パスワード
				.defaultSuccessUrl("/user/list", true) // ログイン成功後遷移先
				.failureUrl("/login?error") // ログイン失敗時遷移先
				.permitAll()
			)
			// ★ ログアウト設定
			.logout(logout -> logout
				.logoutUrl("/logout") // ログアウトURL
				.logoutSuccessUrl("/login?logout") // ログアウト後遷移先
				.invalidateHttpSession(true) // セッション破棄
				.deleteCookies("JSESSIONID") // Cookie削除
				.permitAll()
			);
		
		return http.build();
	}
	
	/**
	 * パスワードエンコーダー
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
