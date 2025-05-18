package org.example;

import org.example.queries.*;
import org.example.queries.Error;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ManagerServerQuery {

    private final Application app;
    private ArrayList<Query> queries = new ArrayList<>();

    public ManagerServerQuery(Application app) {
        this.app = app;
        queries.add(new AllDone(app));
        queries.add(new Error(app));
        queries.add(new Exit(app));
        queries.add(new GetFileName(app));
        queries.add(new GetId(app));
        queries.add(new GetProduct(app));
        queries.add(new NotFoundObject(app));
        queries.add(new RepeatLastCommand(app));
        queries.add(new WrongCommand(app));
        queries.add(new WrongData(app));
        queries.add(new FinishReadingFile(app));
    }

    public void callQuery(ObjectInputStream objStream) throws IOException, ClassNotFoundException {
        if (objStream == null) {
            System.out.println("The server is not responding.");
            return;
        }
        while (true) {
            QueryWrapper query = (QueryWrapper) objStream.readObject();
            for (Query<?> current : this.queries) {
                if (current.getQuery().equals(query.getQueryType())) {
                    current.execute(objStream);
                    return;
                }
            }
        }
    }
}