package org.pokemon.Model;

public class Type {
    private int typeId;
    private String typeName;
    private String typeWeakness;

    public Type() {
    }

    public Type(int typeId, String typeName, String typeWeakness) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeWeakness = typeWeakness;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeWeakness() {
        return typeWeakness;
    }

    public void setTypeWeakness(String typeWeakness) {
        this.typeWeakness = typeWeakness;
    }

    @Override
    public String toString() {
        return "Type: " + typeName + System.lineSeparator() +
                "Weakness: " + typeWeakness;

    }
}
