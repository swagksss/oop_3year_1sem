package org.example.entity;

import java.util.Objects;

public class Drug implements Comparable<Drug> {
    private int id;
    private String name;
    private String pharmName;
    private Group group;
    private String analogs;
    private Version version;

    // Getter method for retrieving the drug's ID
    public int getId() {
        return id;
    }

    // Getter method for retrieving the drug's name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the pharmaceutical name of the drug
    public String getPharmName() {
        return pharmName;
    }

    // Getter method for retrieving the drug's group
    public Group getGroup() {
        return group;
    }

    // Getter method for retrieving analogs of the drug
    public String getAnalogs() {
        return analogs;
    }

    // Getter method for retrieving the drug's version
    public Version getVersion() {
        return version;
    }

    // Setter method for setting the drug's ID
    public void setId(int id) {
        this.id = id;
    }

    // Setter method for setting the drug's name
    public void setName(String name) {
        this.name = name;
    }

    // Setter method for setting the pharmaceutical name of the drug
    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }

    // Setter method for setting the drug's group
    public void setGroup(Group group) {
        this.group = group;
    }

    // Setter method for setting analogs of the drug
    public void setAnalogs(String analogs) {
        this.analogs = analogs;
    }

    // Setter method for setting the drug's version
    public void setVersion(Version version) {
        this.version = version;
    }

    // toString method to provide a string representation of the Drug object
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append('\n');
        result.append(pharmName).append('\n');
        result.append(group).append('\n');
        result.append(analogs).append('\n');
        result.append(version).append('\n');
        return result.toString();
    }

    // Comparable interface implementation for comparing Drug objects based on version certificate
    @Override
    public int compareTo(Drug o) {
        return Integer.compare(this.version.getCertificate(), o.getVersion().getCertificate());
    }

    // Equals method to compare two Drug objects for equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return id == drug.id &&
                name.equals(drug.name) &&
                pharmName.equals(drug.pharmName) &&
                group == drug.group &&
                analogs.equals(drug.analogs) &&
                version.equals(drug.version);
    }

    // Hash code calculation for consistent hashing of objects
    @Override
    public int hashCode() {
        return Objects.hash(id, name, pharmName, group, analogs, version);
    }
}

