import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static B convertAtoB(List<A> list) {
        Map<Integer, B> map = new HashMap<>();
        for (A a : list) {
            map.put(a.getId(), new B(a.getId(), a.getName(), new ArrayList<>()));
        }
        B root = null;
        for (A a : list) {
            B parent = map.get(a.getParentId());
            if (parent != null) {
                parent.getChildren().add(map.get(a.getId()));
            } else {
                root = map.get(a.getId());
            }
        }
        return root;
    }

    public static void main(String[] args) {
        ArrayList<A> arrayList = new ArrayList<>();
        A a = new A(1, "A", 0);
        A b = new A(2, "B", 1);
        A c = new A(3, "C", 1);
        arrayList.add(a);
        arrayList.add(b);
        arrayList.add(c);
        B b1 = convertAtoB(arrayList);
        System.out.println(b1);

    }
}

class A {
    private int id;
    private String name;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public A(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}

class B {
    private int id;
    private String name;
    private List<B> children;

    public B(int id, String name, List<B> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<B> getChildren() {
        return children;
    }

    public void setChildren(List<B> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}