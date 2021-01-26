package ai.auctus.jokenpo.service;

public enum JokenpoEnum implements Jokenpo {
    PEDRA {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(PEDRA);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(SPOCK) ||
                    jokenpoEnum.equals(PAPEL);
        }
    },
    PAPEL {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(SPOCK)
                    || jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(PAPEL);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(TESOURA);
        }
    }, TESOURA {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(TESOURA);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(SPOCK);
        }
    }, LAGARATO {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(SPOCK)
                    || jokenpoEnum.equals(LAGARATO);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(TESOURA);
        }
    }, SPOCK {
        @Override
        public Boolean venceOuEmpata(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(SPOCK);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(PAPEL);
        }
    }
}
