package cs3035unb.cs3035examplecode.GetNotifiedOfChangeInSimpleList;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final ObservableList<Fruit> fruit = FXCollections.observableArrayList(
                new Callback<Fruit, Observable[]>() {
                    @Override
                    public Observable[] call(Fruit param) {
                        return new Observable[]{
                                param.nameProperty(),
                                param.descriptionProperty()
                        };
                    }
                }
        );

        fruit.addListener(new ListChangeListener<Fruit>() {
            @Override
            public void onChanged(Change<? extends Fruit> c) {
                while (c.next())
                {
                    if (c.wasPermutated())
                    {
                        for (int i = c.getFrom(); i < c.getTo(); ++i)
                        {
                            System.out.println("Permuted: " + i + " " + fruit.get(i));
                        }
                    }
                    else if (c.wasUpdated())
                    {
                        for (int i = c.getFrom(); i < c.getTo(); ++i)
                        {
                            System.out.println("Updated: " + i + " " + fruit.get(i));
                        }
                    }
                    else
                    {
                        for (Fruit removedItem : c.getRemoved())
                        {
                            System.out.println("Removed: " + removedItem);
                        }
                        for (Fruit addedItem : c.getAddedSubList())
                        {
                            System.out.println("Added: " + addedItem);
                        }
                    }
                }
            }
        });

        Fruit apple = new Fruit("Apple", "Green Skin");
        Fruit pear = new Fruit("Pear", "Yellow Skin");

        fruit.addAll(apple, pear);

        fruit.get(0).setDescription("Red Skin");

        fruit.add(
                new Fruit("Peach", "Giant")
        );

        fruit.remove(1);

        System.out.println(
                fruit.stream()
                        .map(Fruit::toString)
                        .collect(Collectors.joining("\n"))
        );
    }
}
