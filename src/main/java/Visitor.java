public class Visitor {
    private int age;
    private float weight;
    private float height;
    private Guardian guardian;
    public Visitor(int age, float weight, float height, Guardian guardian) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.guardian = guardian;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }
}
