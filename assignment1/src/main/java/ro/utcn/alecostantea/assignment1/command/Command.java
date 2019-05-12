package ro.utcn.alecostantea.assignment1.command;

import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;

public interface Command {

    void execute(RepositoryFactory factory);
}
