public class MutableInteger {
    private int servingCount;   // Number of servings - the savages will try to finish this, the cook will fill it

    public MutableInteger(int servingCount){
        this.servingCount = servingCount;
    }

    public int getServingCount() {
        return servingCount;
    }

    public void setServingCount(int servingCount) {
        this.servingCount = servingCount;
    }
}
