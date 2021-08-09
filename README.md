# Site de duel de pierre-feuille-ciseaux

# Idée du site 
- Page d'accueil avec une liste de toutes les personnes en attente d'un adversaire
    - Afficher les parties de la plus ancienne à la plus récente afin d'éviter d'attendre trop longtemps
    - Une fois un adversaire choisis, la partie se lance, chaque joueur choisis son jeu.
    - Lorsque les deux joueurs ont fait leur choix, le gagnant est annoncé
- Création d'une partie avec 
- Profile par joueur avec quelques statistiques : 
    - Nombre de victoires / défaites
    - Elo ? 

# Architecture

## Back-end (Spring boot)
- Routes (Assumés GET par défaut)
    - Gestion des parties
        - /game (POST)
            - Permet de créer une nouvelle partie. Une nouvelle partie vide est renvoyée.
        - /game
            - Permet de récupérer l'ensemble des parties. Plusieurs filtres sont disponibles (**TODO**: unique un à la fois). Il sont à passés dans le Query String de la requête.
                - ended : Renvoie les parties terminés.
                - playing : Renvoie les parties en court.
                - sort : Renvoie les parties triés commençant par la plus récente.
        - /game/<id>
            - Renvoie les informations de la partie ayant l'ID <id>.
            - _Note_: Pour les parties en cours, les deux tours sont nuls afin d'éviter toute triche.
        - /game/<gameId>/<play> (POST)
            - Permet de jouer un coup. 
            - <gameId> correspond à la partie dans laquelle on souhaite jouer.
            - <play> correspond au coup que l'on veut jouer. Il peut être 'p' (Pierre), 'f' (Feuille) ou 'c' (Ciseaux).
            - **Attention** : Cette route peut renvoyer des erreurs dans le cas où <play> ou <gameId> n'est pas valide ainsi que si joueur essaye de jouer contre lui-même.
            - Le joueur est déterminé à l'aide de son Token présent dans l'en-tête de la requête. (Voir [Authentification](#Authentification))

## Front-end (Angular)
- Routes:
    - / 
        - Page d'accueil listant les parties disponibles.
    - /game/<id>
        - Permet de voir l'état d'une partie terminée. Si la partie n'est pas terminé, aucune donnée n'est affichée, cela permet de ne pas savoir quel coup est joué par l'adversaire à l'avance.
    - /play/<id> 
        - Page sur laquelle le joueur choisis son coup. Les choix de Pierre, Feuille et Ciseaux sont disponible.
    - /profil/<username>
        - Afficher le profil de l'utilisateur <username>. Plusieurs informations tel que le nombre total de partie, de victoires et de défaites sont affichées.

## Authentification
Un simple jeton Basic est utilisé par le Front-End afin de s'identifier. Il est transmit automatique dans l'en-tête de toutes les requêtes.
Le jeton est composé de la forme 
```javascript
window.btoa(username + ":" + password);
```

Ce n'est évidemment pas la façon la plus sécurisée mais cela permet d'avoir un système d'identification utilisable.

# A faire
- [ ] Préparer le design du site
- [ ] Profil d'un utilisateur
    - [X] Calculer les données
        - [X] Nombre de parties, victoires et défaite
    - [ ] L'afficher sous forme de graphique
- [ ] Système de classement
- [ ] Filtre des parties
- [ ] Recherche de profil
- [ ] Demande en duel (?)
- [ ] Compléter la Doc
- [ ] Faire un DOC de l'api sur la route /api/
- [X] Page d'authentification
- [ ] Persistance de l'authentification
