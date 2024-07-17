This project is intended as a barebones Kotlin plugin template for PaperMC
- Nothing that is not specifically essential should be added here.
- Gradle should expand most plugin.yml base variables, specially those that are shared somehow.

I found most templates out there using kotlin were outdated or had way too much stuff in them,
which is not bad, but I prefer to just change as few things as possible and be ready to go.
Another reason is most people just use a proper IDE for Java/Kotlin and I use Neovim because
I don't value my sanity, therefore I get to setup everything manually :).

### Setup
- Delete flake.nix and flake.lock if you're not using nix.

- Replace group and name variables on the build file, and move your project such that
your main file is on `src/main/kotlin/your/group/name/projectname/ProjectName.kt`

- Change API version on plugin.yml and the paper repository.

- Replace the package and class name on your name file and it should be done.

No need to change anything else in plugin.yml since that's somewhat done from build.gradle

If I ever feel like it I might make different branches for different things.
