@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    //acceso público
                    .requestMatchers("/", "/productos/menu", "/css/**", "/js/**").permitAll()

                    //acceso por ROLES
                    .requestMatchers("/pedidos/nuevo").hasAnyRole("CLIENTE", "ADMIN")
                    .requestMatchers("/pedidos/historial").hasAnyRole("CLIENTE", "ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN") //solo admin puede gestionar estados

                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .defaultSuccessUrl("/pedidos/historial", true)
                    .permitAll()
            )
            .logout(logout -> logout.permitAll());

    return http.build();
}