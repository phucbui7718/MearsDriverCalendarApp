package entities;


public enum DriverRequestType {
    DAYOFF(1) {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "Day Off";
        }

        @Override
        public int getEarliestDayToSubmitRequest() {
            return 60;
        }

        @Override
        public int getLatestDayToSubmitRequest() {
            return 0;
        }
    },
    WORK(2){
        @Override
        public int getId() {
            return 2;
        }

        @Override
        public String getDescription() {
            return "Work";
        }

        @Override
        public int getEarliestDayToSubmitRequest() {
            return 60;
        }

        @Override
        public int getLatestDayToSubmitRequest() {
            return 7;
        }
    };
    private int value;

    public abstract int getId();
    public abstract String getDescription();
    public abstract int getEarliestDayToSubmitRequest();
    public abstract int getLatestDayToSubmitRequest();

    private DriverRequestType(int n) {
        this.value = n;
    }
}
