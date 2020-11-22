package fr.isen.m1.tourament.console;

public interface ICommand {
    // Renvoie le message d'aide qui sera affiché sur la page d'aide de la commande
    String getHelpMessage();

    // Fonction executé par le CommandHandler quand la commande a été trouvé.
    void run(CommandContext cmdContext);

    // Renvoie le nom de la commande à ecrire afin d'accéder à la fonction run()
    // Ne pas oublier d'écrire le préfix de la commande en cas de sous-commande
    // Exemple: "league create <args...>". "league" est le prefix et "create" est le nom de la commande
    String getCommandName();
}
