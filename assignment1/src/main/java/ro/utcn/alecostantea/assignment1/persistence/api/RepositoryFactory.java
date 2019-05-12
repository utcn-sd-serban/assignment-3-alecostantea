package ro.utcn.alecostantea.assignment1.persistence.api;



public interface RepositoryFactory {

    QuestionRepository createQuestionRepository();

    UserRepository createUserRepository();

    TagRepository createTagRepository();
}
