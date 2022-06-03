package br.com.digisystem.utils;

import br.com.digisystem.entities.ProfessorEntity;

public class ProfessorUtil {

protected ProfessorEntity createValidProf() {
		
		ProfessorEntity prof = new ProfessorEntity();
		
		prof.setCpf("142121321421");
		prof.setEmail("test@test.com");
		prof.setNome("test");
		prof.setTelefone("432434643");
		
		return prof;
	}
}
