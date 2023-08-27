package cs3035unb.cs3035examplecode.GetNotifiedOfChangeInSimpleList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Fruit {
    final StringProperty name;
    final StringProperty description;

    public Fruit(String name, String description) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fruit{");
        sb.append("name=").append(name.get());
        sb.append(", description=").append(description.get());
        sb.append('}');
	    return sb.toString();
    }
}
