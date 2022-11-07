package org.springframework.samples.petris.auditoria;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuditoriaImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName())
				.filter(s -> !s.isEmpty());
	}

}
