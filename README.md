# Life Simulator

Program to generate a one-dimensional world, with beings who live and die by certain rules, 
decide whether to move or eat based on their DNA code, and reproduce with random mutation of their DNA.

Watch evolution happening :)

## Running it

Download the [executable jar](http://victor.combal-weiss.eu/lifesimulator/LifeSimulator.jar).

In a terminal, cd to its directory and run :
``` sh
java -jar LifeSimulator.jar
```

## Code documentation

Available at http://victor.combal-weiss.eu/lifesimulator/doc

## Available commands

<table>
  <tr>
    <td>
      iterate | iterate once
    </td>
    <td>
      Simulate passage of time for 1 cycle. Distribute food, declare dead beings who should, breed, etc
    </td>
  </tr>
  <tr>
    <td>
      iterate &lt;x&gt;
    </td>
    <td>
      Simulate passage of time for &lt;x&gt; cycles.
    </td>
  </tr>
  <tr>
    <td>
      display | display world
    </td>
    <td>
      Display state of the world, list beings and species
    </td>
  </tr>
  <tr>
    <td>
      display date
    </td>
    <td>
      Display current world's date
    </td>
  </tr>
  <tr>
    <td>
      display number of beings
    </td>
    <td>
      Display number of living beings
    </td>
  </tr>
  <tr>
    <td>
      display population
    </td>
    <td>
      Display summary of all living beings
    </td>
  </tr>
  <tr>
    <td>
      display being &lt;x&gt;
    </td>
    <td>
      Display information about being with id &lt;x&gt;
    </td>
  </tr>
  <tr>
    <td>
      display number of species
    </td>
    <td>
      Display number of living species
    </td>
  </tr>
  <tr>
    <td>
      display species &lt;x-x-x&gt;
    </td>
    <td>
      Display information, including DNA, about species with code &lt;x-x-x&gt;
    </td>
  </tr>
  <tr>
    <td>
      display species all
    </td>
    <td>
      Display summary information on all non-extinct species
    </td>
  </tr>
  <tr>
    <td>
      display DNA composition
    </td>
    <td>
      Display total number of each type of DNA code element, across all living beings
    </td>
  </tr>
  <tr>
    <td>
      quit
    </td>
    <td>
      Exit the program
    </td>
  </tr>
  <tr>
    <td>
      help | h
    </td>
    <td>
      Display available commands
    </td>
  </tr>
</table>

## Rules of the world

 * World characteristics
   * World is 1-dimensional, with integer positions
   * World has 1 hole, at a fixed position
   * Food is being distributed randomly at each cycle
 * Beings life events
   * Beings have a given max lifetime
   * A being dies before max lifetime if :
     * it walks into the hole
     * it doesn't eat for a given period of time
 * Beings behavior
   * Each being has its own DNA, represented by a program of a very simple language
   * At each cycle, each being's DNA is interpreted, to infer being's decision to move either left or right, and to eat
 * Breeding
   * At each cycle, with a given probability a being breeds
   * When it does, its child receives its DNA, which incurs a mutation with a given probability

## License

    Copyright 2012-2016 Victor Combal-Weiss

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
