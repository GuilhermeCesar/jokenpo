package ai.auctus.jokenpo.service;

public enum JokenpoEnum implements Jokenpo {
    PEDRA {
        @Override
        public Boolean vence(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(LAGARATO);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(SPOCK) ||
                    jokenpoEnum.equals(PAPEL);
        }
    },
    PAPEL {
        @Override
        public Boolean vence(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(SPOCK)
                    || jokenpoEnum.equals(PEDRA);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(TESOURA);
        }
    }, TESOURA {
        @Override
        public Boolean vence(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(LAGARATO);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(SPOCK);
        }
    }, LAGARATO {
        @Override
        public Boolean vence(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PAPEL)
                    || jokenpoEnum.equals(SPOCK);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(PEDRA)
                    || jokenpoEnum.equals(TESOURA);
        }
    }, SPOCK {
        @Override
        public Boolean vence(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(TESOURA)
                    || jokenpoEnum.equals(PEDRA);
        }

        @Override
        public Boolean perde(JokenpoEnum jokenpoEnum) {
            return jokenpoEnum.equals(LAGARATO)
                    || jokenpoEnum.equals(PAPEL);
        }
    }
}
