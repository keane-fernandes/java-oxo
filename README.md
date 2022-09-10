<h1 align="center">
  <br>
    <img src=./docs/logo.jpg alt="OXO" width="100"></a>
  <br>
  OXO
  <br>
</h1>

<h4 align="center">A digital version of the classic turn-taking game "Noughts and Crosses"  built in Java.</h4>

<p align="center">
  <a href="#Features">Key Features</a> |
  <a href="#Usage">Usage</a> |
  <a href="#Design">Design</a> |
  <a href="#License">License</a>
</p>

<p align="center">
<img src="./docs/logo.jpg/../OXO.gif" />
</p>

# Key Features

- Fully customisable asymmetrical grids (3x3 upto 9x9)
- Fully customisable win thresholds
- Exceptions for invalid moves

# Usage
The game supports Java 8+ and can be run using IntelliJ. The game can be launched from the OXOGameClass.

# Design
The game adopts the MVC (Model View Controller) architecture.

The OXOModel class contains the core data structures for the game - the public methods provided by this class are used to manipulate the internal state of the game.

The OXOView class is responsible for the "Rendering Logic" - any changes to the state of the OXOModel will be automatically rendered in the OXOView.

The OXOController class handles all of the event handling logic in the game.

The OXO Tester class contains all of the unit tests written for the application.  

# License

MIT License