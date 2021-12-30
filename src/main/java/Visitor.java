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
}
