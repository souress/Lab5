package utils;

import java.util.HashSet;
import java.util.Random;

public class IdGenerator {
    private static final HashSet<Integer> hashSetId = new HashSet<>();

    public static Integer generateId() {
        Integer id = new Random().nextInt(Integer.MAX_VALUE);

        if (hashSetId.contains(id)) {
            while (hashSetId.contains(id))
                id = new Random().nextInt(Integer.MAX_VALUE);
        }

        hashSetId.add(id);
        return id;
    }
}
