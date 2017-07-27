package com.d4vinci.stories.services;

import com.d4vinci.stories.models.Story;

/**
 * Created by D4Vinci on 7/17/2017.
 */

public class DummyStories {
    public static Story[] getDummyStories() {
        return new Story[] {new Story("As someone who loves to travel, " +
                "consider taking a sporadic trip somewhere new.",
                "Tsortanidis Christos",
                "https://media.licdn.com/mpr/mpr/p/2/005/0a1/14e/27c77d0.jpg"),
                new Story("I love to explore old buildings to see what I can find." +
                        "There are always discoveries made!",
                        "John Van Halen",
                        "https://www.colourbox.com/preview/10554637-traditional-italian-homes.jpg"),
                new Story("There's nothing better than a beachside chill day." +
                        "Amazing way to spend some time.",
                        "Axl Chrysanthemum",
                        "http://www.caribefuntours.com/wp-content/uploads/2016/07/Banana-Azul-Relaxing-Beachside-Massage-Slide-3.jpg")};
    }
}
