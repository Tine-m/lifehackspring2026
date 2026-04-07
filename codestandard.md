# Kodestandard for Lifehack forår 2026

Formålet er at sikre:

- Overskuelig kode
- Ensartet struktur
- Lettere samarbejde
- Lettere feedback og bedømmelse

---

## 1. Overordnet struktur

#### **JDK 21** bruges af alle grupper.

##### Hvis du tilføjer dependencies må de ikke overskrive en anden.

##### Alt navngives på **engelsk** i koden. Kun ledetekster og fejlbeskeder skal være på dansk.

Alle arbejder på samme GitHub repository.

Projektet underopdeles i packages:

```
app/
├── Main.java
├── config/
├── controllers/
├── entities/
├── persistence/
├── exceptions/
└── services/

```

---

## 2. Team-opdeling

Hver gruppe skal placere deres kode i egne **team-subpackages**.

Eksempel (teamA):

```
app/controllers/teamA/
app/entities/teamA/
app/persistence/teamA/
```

---

## 3. Navngivning

### Klasser (PascalCase)

```java
PostController
UserMapper
Post
```

### Metoder og variabler (camelCase)

```java
createUser()
getAllPosts()
postTitle()
userList()
```

### Undgå

```java
Team1PostController   ❌
User_controller       ❌
```

> 👉 Team-navn skal fremgå af **package navn**, ikke af klassens navn.

### Tabeller (snake_case)

Tabelnavne skal skrives med lowercase og have teamnavn som præfix. F.eks.

```sql
teama_quotes
```

Hvis et team har brug for login funktionalitet, så laver de det selv

---

## 4. Routing
Routing skal foregå i controllers og hver gruppe SKAL sætte teamnavn foran sin route (URL-prefix).

Eksempel:

```
/teamA/posts
/teamA/users
/teamB/tasks
```

### Route-opsætning i `Main.java`:

```java
app.get("/", ctx -> ctx.render("index.html"));

TeamAController.addRoutes(app, connectionPool);
TeamBController.addRoutes(app, connectionPool);
```

---

## 5. HTML og templates

Templates placeres i:

```
src/main/resources/templates/
```

Hver gruppe får sin egen mappe:

```
templates/teamA/
templates/teamB/
```

### Eksempel

```
templates/teamA/index.html
templates/teamA/post-list.html
```

### Navngivning

- Små bogstaver
- Bindestreg som separator

```
post-list.html
create-user.html
```

---

## 6. CSS og assets

I public-mappen må der ligge css-fil, images og evt javascript:
```
resources/public/css/teamA
resources/public/images/teamA
resources/public/js/teamA
resources/public/css/teamB
resources/public/images/teamB
```

---

## 7. Database

Sql scripts lægges i sql mappe under resources, f.eks.:
```
resources/sql/teamA
resources/sql/teamB
```

***Jeres egne scripts*** **MÅ IKKE** indeholde noget vedrørede den generelle `users` tabel, der oprettes via `lifehack.sql` i roden af `sql` mappen.
Hvis I har brug for login på jeres specifikke app mede egne brugere, så opret en tabel til det, f.eks. `teamg_users`;
---
## 8. Java kodeprincipper

### Metoder skal:

- metodenavn skal beskrive metoden så præcist som muligt, men simpelt og så kort som muligt
  (uden forkortelser)
- have ét ansvar

### Eksempel

```java
public static void createPost(Context ctx, ConnectionPool connectionPool) {
    String title = ctx.formParam("title");
    String content = ctx.formParam("content");

    PostMapper.createPost(title, content, connectionPool);
    ctx.redirect("/team1/posts");
}
```

---

## 9. Kommentarer

Kommentarer skrives på engelsk og bruges til:

- TODO-noter (ikke i færdig produkt)
- Kompleks logik kan forklares (Men skal helst undgås, ved korte metoder og gode metodenavne)

---

## 10. Fejlhåndtering

- Skrives på dansk
- Input skal valideres
- Brugeren skal have fejlbeskeder
- Appen må ikke crashe

### Eksempel

```java
try {
    UserMapper.createUser(username, password, connectionPool);
    ctx.redirect("/teamA/login");
} catch (DatabaseException e) {
    ctx.attribute("error", "Brugernavnet findes allerede");
    ctx.render("teamA/create-user.html");
}
```

---

## 11. README (for hver gruppe)

Hver gruppe skal have en kort beskrivelse med:

- Skrives på engelsk
- Gruppenavn
- Medlemmer
- Appens funktionalitet

---

## 12. Git regler

### I må:

- Arbejde i jeres egne packages
- Lav commits ved nye tilføjelser med gode beskrivelser

### I må ikke:

- Ændre i andre gruppers kode uden aftale

### Gode commit-beskeder

```
Add post entity
Create user registration
Fix validation bug
```

### Dårlige commit-beskeder

```
fix
done
hej
```

---

## 13. Minimumskrav

Alle projekter skal:
-
- Kunne starte uden fejl
- Have korrekt package-struktur
- Bruge team-underpakker
- Have meningsfulde navne
- Have læsbar kode
- Have basic validering
- Have fungerende routing med eget prefix
- Obviously AI genereret kode godkendes **IKKE**

---

## 14. Fælles forside (index.html)

I skal ændre i `MainController`, hvis jeres app skal med på forsiden af web site.

---

## Kort opsummering

- ✅ Brug team-underpakker
- ✅ Brug fælles struktur
- ✅ Brug eget URL-prefix
- ✅ Hold koden simpel og læsbar
- ✅ Engelsk er et krav

---

## 15. Testautomatisering

> ingen Krav om unit tests og integrationstests — Men må gerne implementeres

---

## 16. Andet

> Yderligere regler kan tilføjes efter behov. Dette vil blive annonceret gennem discord.
