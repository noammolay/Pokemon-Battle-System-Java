package pokemon;

import java.io.Serializable;

public class Type implements Comparable<Type>, Serializable {
    private String type_name;

    //constructor.
    public Type(String type) {
        if (type == null) {
            this.type_name = "Water";
        } else {
            this.type_name = type;
        }
    }

    public String getType() {
        return this.type_name;
    }
    // compares this type to another type according to type_name.
    @Override
    public int compareTo(Type other) {
        if (other == null){
            return 1;
        }
        return switch (this.type_name) {
            case "Fire" -> switch (other.type_name) {
                case "Water" -> -1;
                case "Grass", "Electric" -> 1;
                default -> 0;
            };
            case "Water" -> switch (other.type_name) {
                case "Fire" -> 1;
                case "Grass", "Electric" -> -1;
                default -> 0;
            };
            case "Grass" -> switch (other.type_name) {
                case "Fire" -> -1;
                case "Water", "Electric" -> 1;
                default -> 0;
            };
            case "Electric" -> switch (other.type_name) {
                case "Fire", "Grass" -> -1;
                case "Water" -> 1;
                default -> 0;
            };
            default -> 0;
        };

    }
}
