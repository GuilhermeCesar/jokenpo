package ai.auctus.jokenpo.service;

public enum JokenpoEnum implements Jokenpo {
    PEDRA {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(PEDRA);
        }
    },
    PAPEL {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(SPOCK)
                    || jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(PAPEL);
        }
    }, TESOURA {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(TESOURA);
        }
    }, LAGARATO {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(SPOCK)
                    || jokenpoEnum.equals(LAGARATO);
        }


    }, SPOCK {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(SPOCK);
        }
    }
}
