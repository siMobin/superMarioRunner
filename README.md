<div align="center">
<img src="ASSETS/repository-open-graph-template of Super Mario Runner [Master Head].jpg" width="100%" alt="Super Mario Runner java-game java siMobin java-project game game-project mobin github git">
<h1 style="text-align:center; color:red; font-weight:bold">Super Mario Runner</h1>
<p style="color:gray; font-weight:bold">
A fusion of two gaming classics with Super Mario Runner! This Java-based recreation seamlessly blends the timeless charm of the Chrome Dino game with the vibrant world of Super Mario Bros.
</p>
</div>

###

### Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [System Requirements](#system-requirements)
- [Contribution](#contribution)
- [Folder Structure](#folder-structure)
- [Dependency Management](#dependency-management)
- [License](#license)


## Features
+ [x] A Super Mario-style environment
+ [x] Score tracking
+ [x] Pause/Resume
+ [x] Key bindings for all actions
+ [x] Randomized obstacle spawning
+ [x] Collision detection & death/game-over
+ [x] Audio & background music
+ [x] Toggle microphone (for audio feedback)
+ [x] Debug mode for easier testing

- [ ] Power-ups
- [ ] Flying obstacles
- [ ] Gamepad support
- [ ] 3D modeling & vector graphics
- [ ] Mobile device & touch screen support
- [ ] And more...


## Getting Started
To start the game, simply run the `App` class.


## System Requirements
**OS:** Windows 10 (64-bit) or later  
**CPU:** Intel® 6th Generation or newer or AMD Ryzen™ 1000 Series or newer  
**GPU:** Interegrated Graphics (IGPU, APU) with a minimum of 128MB VRAM  
**Memory:** *\*Unspecified*  
**Storage:** 100MB of free space for *Development*, or \*35MB of free space for *Production/Release/Playing-condition*   
**Display:** Dedicated display with a refresh rate of 60Hz or better  
**JDK/JVM:** OpenJDK 20 or later  
<sub>\* Memory requirements depend on *JVM* and other *system configurations*.</sub><br>
<sub>\* Additional 25MB required for *git* clone.</sub>


## Contribution
- #### Clone the repository by running:
```shall
git@github.com:siMobin/superMarioRunner.git
```

>[!NOTE]
>If you don't have an `SSH` key set up, you can also run:
>```
>https://github.com/siMobin/superMarioRunner.git
>```
>
>Or, you can use **GitHub CLI**:
>```
>gh repo clone siMobin/superMarioRunner
>```

- #### Install the recommended extensions on VS Code:
  [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

- #### Compile the source code:
  You can compile it through the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), or run the following commands:
  ```shall
  javac App.java
  java App.class
  ```
  or Simply run the following scripts:
  ```ps1
  compiler.ps1
  run.ps1
  ```

<!-- download doc -->
<a href="./docs/Document.pdf" download>Download </a>the full [documentation](./docs/Document.docx)

## Folder Structure
The workspace contains three folders by default, where:

- `src`: the folder to maintain ***sources***
- `lib`: the folder to maintain ***dependencies*** & ***assets***
- `bin`: the folder to store all ***binary***/***class*** file

The compiled output files will be generated in the `bin` folder by default.

> [!NOTE]
> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management
>[!IMPORTANT]
>External dependencies should be placed directly into the `lib` folder.

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## License
This project is *licensed* under the **BSD 3-Clause** License. See **[LICENSE](LICENSE)** for details.