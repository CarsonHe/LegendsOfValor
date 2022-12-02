# CS611-Assignment4
## Legends: Legends of Valor
---------------------------------------------------------------------------
Derek Dumouchel
ddumouch@bu.edu
U24217400

Haijun He
haijunhe@bu.edu

##Files
---------------------------------------------------------------------------
Main.java:
- Class with main method to launch game playing session.

Game.java:
- Abstract class with play and quit methods for extended classes (LegendsOfValorGame)

LegendsOfValorGame.java:
- Extended class of Game.java containing rules for playing Legends Of Valor Game
- Methods included for intializing the playing map/board and monsters
- Methods for forming a hero team, respawning and assigning a hero for monster to attack
- Methods for heroes to take turn (move/attack/market/inventory etc.) and check if valid turn
- Methods to check if game is over or if the user quits the game.

ValorMap.java:
- Class which creates Map made up of cell objects for playing Legends Of Valor Game
- Initializes accessible/inaccessible cells, random accessible cell types (bush, cave, Koulou)
- Method included for printing the map

Cell.java:
- Base class of which cell types are extended.
- Sets cell types for attack bonusus (where applicable)
- Tracks whether cell contains monster/hero
- Sets boarders for map printing

InaccessibleSpell.java:     extends cell            sets cell type to prevent hero access
PlainCell.java:             extends cell            sets cell as plain for printing and no bonus
BushCell.java:              extends cell            sets cell as Bush for printing and bush bonus
CaveCell.java:              extends cell            sets cell as Cave for printing and cave bonus
KoulouCell.java:            extends cell            sets cell as Koulou for printing and koulou bonus
NexusCell.java:             abstract, extends cell  extended for hero/monster nexus. set print border
HeroNexusCell.java:         extends NexusCell       sets cell as nexus for printing. intial to have hero
MonsterNexusCell.java:      extends NexusCell       sets cell as nexus for printing. intial to have Monster


Role.java:
- base class extended by heroes and monsters. Track location on map of hero/monster
- get/set name, level, hp, row, column

HeroList.java:
- Sets array list of heroes with stats for choice by user at start of game. 
- Method for printing list for user to choose a Hero (Warrior, Paladin, Sorcerer)

MonsterList.java:
- Sets array list of monsters with stats for random creation. (Dragon, Exoskeleton, Sprit)
- Method for printing list for user to choose a Hero

HeroTeam.java:
- class to create team of three heroes

Hero.java
- Class containing statistics of hero object (mana, strength, dexterity, agility, money, xp,  etc.)
- Array lists attribute for inventory carried by each hero object. (potions, weapons, armor, spells)
- Methods for leveling up, buying/selling items (spells, potions, armor, weapons) and printing inventory
- Methods for equiping armor and weapons, using potions, and casting spells
- Methods for heroes to attack monsters when able to or to move/teleport/return to nexus and valid cells

Paladin.java:       extends Hero, sets hero stats and hero type for HeroLevelUpHandler (specific to hero type)
Sorcerer.java:      extends Hero, sets hero stats and hero type for HeroLevelUpHandler (specific to hero type)
Warrior.java:       extends Hero, sets hero stats and hero type for HeroLevelUpHandler (specific to hero type)

HeroLevelUpHandler.java:
- Interface implemented by AbstractLevelUp. Ensure methods to check if can level up and to level up

AbstractLevelUp.java:   implements HeroLevelUpHandler   check if hero can level up, set hero stats
PaladinLevelUp.java:    extends AbstractLevelUp         for leveling up hero with specialty bonus
SorcererLevelUp.java:   extends AbstractLevelUp         for leveling up hero with specialty bonus
WarriorLevelUp.java:    extends AbstractLevelUp         for leveling up hero with specialty bonus

Monster.java:
- Class containing statistics of monster object (damage, defenseStat, dodgeChance) 
- Methods to determine hero to attack and when monster if monster is dead.  

Dragon.java:        extends monster, sets monster stats
Exoskeleton.java:   extends monster, sets monster stats
Sprit.java:         extends monster, sets monster stats

Market.java:
- Contains array lists attribute for inventory the market object can sell to hero (potions, weapons, armor, spells)
- Methods for printing stats of each item

TradeInterface.java:    - Interface dictating isAffordable for items in market for hero to buy 
DamageInterface.java:   - Interface dictating damage for items (weapons, spells) in market for hero to buy 

Item.java:
- Class implements TradeInterface with attributes for item name, cost, and required hero level for purchase. 

Spell.java:             implements DamageInterface. class for spell base damage and mana cost
FireSpell.java:         extends spell, calls super constructor to set spell stats and name
IceSpell.java:          extends spell, calls super constructor to set spell stats and name
LightningSpell.java:    extends spell, calls super constructor to set spell stats and name

weapon.java:            extends item, implements DamageInterface, sets base damage/number of hands for use by hero
Armor.java:             extends item, sets damage reduction of item for hero when attacked
Potion.java:            extends Item, sets potion attribute and attribute adder when used on hero

ScannerUtil.java:       class for user input throughout the game

## Notes
---------------------------------------------------------------------------
1. <Files to be parsed should be stored in ConfigFiles, for parser class to
    read class>         N/A
2. <Bonus Done>         N/A
3. <Notes to grader>    N/A

## How to compile and run
---------------------------------------------------------------------------
From IDE:
1. Navigate to the directory containing assignmnet 4 , Legends Of Valor files.
2. Open Main.java and Run public static void main method 

From window command prompt
1. cd to directory containing assignment 4, Legends Of Valor java files. 
2. Type javac Main.java and hit enter
3. Type java main and hit enter

## Input/Output Example
---------------------------------------------------------------------------
<Place here an example of how the program runs. Include both its
outputs and correctly formatted inputs. Please clearly mark the inputs.>

<OUTPUT>
Welcome to Legends of Valor! 
In this game you can form a team as 3 heroes to fight the monster.
initial cell is your nexus where you can buy or sell items.
you can return to nexus whenever you want.
you can not access to all inaccessible cell!

-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
----------------------------------------------------
                                         <<Hero List>>
Warriors:
name                       HP    level      mana         strength       dexterity      agility       money        exp
---------------------------------------------------------------------------------------------------
1. Gaerdal_Ironhand       100      1      100       700          500         600       1354     7    
2. Sehanine_Monnbow       100      1      600       700          800         500       2500     8    
3. Muamman_Duathall       100      1      300       900          500         750       2546     6    
4. Flandal_Steelskin      100      1      200       750          650         700       2500     7    
5. Undefeated_Yoj         100      1      400       800          400         700       2500     7    
6. Eunoia_Cyn             100      1      400       700          800         600       2500     6    
---------------------------------------------------------------------------------------------------
Sorcerers:
name                       HP    level      mana         strength       dexterity      agility       money        exp
---------------------------------------------------------------------------------------------------
7. Rillifane_Rallathil    100      1      1300      750          450         500       2500     9    
8. Segojan_Earthcaller    400      4      900       800          500         2500      2500     5
9. Reign_Havoc            100      1      800       800          800         800       2500     8    
10. Reverie_Ashels        100      1      900       800          700         400       2500     7    
11. Kalabar               100      1      800       850          400         600       2500     6
12. Skye_Soar             100      1      1000      700          400         500       2500     5    
---------------------------------------------------------------------------------------------------
Paladins:
name                       HP    level      mana         strength       dexterity      agility       money        exp
---------------------------------------------------------------------------------------------------
13. Parzival              100      1      300       750          650         700       2500     9
14. Sehanine_Moonbow      100      1      300       750          700         700       2500     9    
15. Skoraeus_Stonebones   100      1      250       650          600         350       2500     4
16. Garl_Glittergold      100      1      100       600          500         400       2500     5    
17. Amaryllis_Astra       100      1      500       500          500         500       2500     5
18. Caliber_Heist         100      1      400       400          400         400       2500     8    
---------------------------------------------------------------------------------------------------
select hero 1 :
<INPUT>
1
<OUTPUT>
select hero 2 :
<INPUT>
7
<OUTPUT>
select hero 3 :
<INPUT>
13
<OUTPUT>
hero List
name                       HP     level    mana      strength          dexterity         agility       money      exp
---------------------------------------------------------------------------------------------------
H1. Gaerdal_Ironhand      100      1      100       700          500         600       1354     7    
H2. Rillifane_Rallathil   100      1      1300      750          450         500       2500     9
H3. Parzival              100      1      300       750          650         700       2500     9    
---------------------------------------------------------------------------------------------------
ready to fight!
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M1. Natsunomeryu          100      1       100       200         0.10
M2. BigBad-Wolf           100      1       150       250         0.15
M3. Natsunomeryu          100      1       100       200         0.10  
---------------------------------------------------------------------------------------------------
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |    M1 |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B

       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P

       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P
  3    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P   

       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B
  4    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B

       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P

       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
  7    | H1    |   |       |   | X   X |   | H2    |   |       |   | X   X |   | H3    |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
v
<OUTPUT>
 welcome to the market!  
1. buy     2. sell     3. finish and end
please select:
<INPUT>
1
<OUTPUT>
welcome to the market, you can buy or sell here
Weapons List
name                   price           level required           base damage          required hands
---------------------------------------------------------------------------------------------------
1. Sword               500            1              800               1
2. Bow                 300            2              500               2    
3. Scythe              1000           6              1100              2    
4. Axe                 550            5              850               1
5. TSwords             1400           8              1600              2
6. Dagger              200            1              250               1    
---------------------------------------------------------------------------------------------------
Armors List
name                      price         level required             damage reduced
---------------------------------------------------------------------------------------------------
7. Platinum_Shield       150            1               200
8. Breastplate           350            3               600           
9. Full_Body_Armor       1000           8               1100
10. Wizard_Shield        1200           10              1500
11. Guardian_Angel       1000           10              1000
---------------------------------------------------------------------------------------------------
Potions List
name                     price          level required            attribute gain           affect attribute
---------------------------------------------------------------------------------------------------
12. Healing_Potion       250            1                100             Health
13. Strength_Potion      200            1                75              Strength 
14. Magic_Potion         350            2                100             Mana
15. Luck_Elixir          500            4                65              Agility
16. Mermaid_Tears        850            5                100             Health|Mana|Strength|Agility
17. Ambrosia             1000           8                150             All Health|Mana|Strength|Dexterity|Defense|Agility
---------------------------------------------------------------------------------------------------
Spells List
name                    price         level required          base damage        mana cost      type
---------------------------------------------------------------------------------------------------
18. Snow_Cannon          500            2             650            250       Ice
19. Ice_Blade            250            1             450            100       Ice
20. Frost_Blizzard       750            5             850            350       Ice
21. Arctic_Storm         700            6             800            300       Ice
22. Flame_Tornado        700            4             850            300       Fire
23. Breath_of_Fire       350            1             450            100       Fire            
24. Heat_Wave            450            2             600            150       Fire
25. Lava_Comet           800            7             1000           550       Fire
26. Hell_Storm           600            3             950            600       Fire
27. Lightning_Dagger     400            1             500            150       Lightning       
28. Thunder_Blast        750            4             950            400       Lightning       
29. Electric_Arrows      550            5             650            200       Lightning
30. Spark_Needles        500            2             600            200       Lightning       
---------------------------------------------------------------------------------------------------
<INPUT>
1
<OUTPUT>
purchase successfully!
 inventory of Gaerdal_Ironhand
name                  price          required level           type
---------------------------------------------------------------------------------------------------
1. Sword               500            1            Weapon
---------------------------------------------------------------------------------------------------
H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
v
<OUTPUT>
 welcome to the market!  
1. buy     2. sell     3. finish and end
please select:
<INPUT>
1
<OUTPUT>
welcome to the market, you can buy or sell here
Weapons List
name                   price           level required           base damage          required hands
---------------------------------------------------------------------------------------------------
1. Sword               500            1              800               1    
2. Bow                 300            2              500               2
3. Scythe              1000           6              1100              2    
4. Axe                 550            5              850               1
5. TSwords             1400           8              1600              2    
6. Dagger              200            1              250               1
---------------------------------------------------------------------------------------------------
Armors List
name                      price         level required             damage reduced
---------------------------------------------------------------------------------------------------
7. Platinum_Shield       150            1               200
8. Breastplate           350            3               600
9. Full_Body_Armor       1000           8               1100
10. Wizard_Shield        1200           10              1500
11. Guardian_Angel       1000           10              1000
---------------------------------------------------------------------------------------------------
Potions List
name                     price          level required            attribute gain           affect attribute
---------------------------------------------------------------------------------------------------
12. Healing_Potion       250            1                100             Health
13. Strength_Potion      200            1                75              Strength 
14. Magic_Potion         350            2                100             Mana
15. Luck_Elixir          500            4                65              Agility  
16. Mermaid_Tears        850            5                100             Health|Mana|Strength|Agility
17. Ambrosia             1000           8                150             All Health|Mana|Strength|Dexterity|Defense|Agility
---------------------------------------------------------------------------------------------------
Spells List
name                    price         level required          base damage        mana cost      type
---------------------------------------------------------------------------------------------------
18. Snow_Cannon          500            2             650            250       Ice
19. Ice_Blade            250            1             450            100       Ice
20. Frost_Blizzard       750            5             850            350       Ice
21. Arctic_Storm         700            6             800            300       Ice
22. Flame_Tornado        700            4             850            300       Fire
23. Breath_of_Fire       350            1             450            100       Fire
24. Heat_Wave            450            2             600            150       Fire
25. Lava_Comet           800            7             1000           550       Fire
26. Hell_Storm           600            3             950            600       Fire
27. Lightning_Dagger     400            1             500            150       Lightning       
28. Thunder_Blast        750            4             950            400       Lightning       
29. Electric_Arrows      550            5             650            200       Lightning
30. Spark_Needles        500            2             600            200       Lightning       
---------------------------------------------------------------------------------------------------
<INPUT>
1
<OUTPUT>
purchase successfully!
 inventory of Rillifane_Rallathil
name                  price          required level           type
---------------------------------------------------------------------------------------------------
1. Sword               500            1            Weapon
---------------------------------------------------------------------------------------------------
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]: 
<INPUT>
w
<OUTPUT>
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
v
<OUTPUT>
 welcome to the market!  
1. buy     2. sell     3. finish and end
please select:
<INPUT>
1
<OUTPUT>
welcome to the market, you can buy or sell here
Weapons List
name                   price           level required           base damage          required hands
---------------------------------------------------------------------------------------------------
1. Sword               500            1              800               1
2. Bow                 300            2              500               2
3. Scythe              1000           6              1100              2    
4. Axe                 550            5              850               1
5. TSwords             1400           8              1600              2
6. Dagger              200            1              250               1
---------------------------------------------------------------------------------------------------
Armors List
name                      price         level required             damage reduced
---------------------------------------------------------------------------------------------------
7. Platinum_Shield       150            1               200
8. Breastplate           350            3               600
9. Full_Body_Armor       1000           8               1100
10. Wizard_Shield        1200           10              1500
11. Guardian_Angel       1000           10              1000
---------------------------------------------------------------------------------------------------
Potions List
name                     price          level required            attribute gain           affect attribute
---------------------------------------------------------------------------------------------------
12. Healing_Potion       250            1                100             Health   
13. Strength_Potion      200            1                75              Strength 
14. Magic_Potion         350            2                100             Mana
15. Luck_Elixir          500            4                65              Agility  
16. Mermaid_Tears        850            5                100             Health|Mana|Strength|Agility
17. Ambrosia             1000           8                150             All Health|Mana|Strength|Dexterity|Defense|Agility
---------------------------------------------------------------------------------------------------
Spells List
name                    price         level required          base damage        mana cost      type
---------------------------------------------------------------------------------------------------
18. Snow_Cannon          500            2             650            250       Ice
19. Ice_Blade            250            1             450            100       Ice
20. Frost_Blizzard       750            5             850            350       Ice             
21. Arctic_Storm         700            6             800            300       Ice
22. Flame_Tornado        700            4             850            300       Fire
23. Breath_of_Fire       350            1             450            100       Fire
24. Heat_Wave            450            2             600            150       Fire
25. Lava_Comet           800            7             1000           550       Fire
26. Hell_Storm           600            3             950            600       Fire
27. Lightning_Dagger     400            1             500            150       Lightning       
28. Thunder_Blast        750            4             950            400       Lightning
29. Electric_Arrows      550            5             650            200       Lightning
30. Spark_Needles        500            2             600            200       Lightning       
---------------------------------------------------------------------------------------------------
<INPUT>
1
<OUTPUT>
purchase successfully!
 inventory of Parzival
name                  price          required level           type
---------------------------------------------------------------------------------------------------
1. Sword               500            1            Weapon
---------------------------------------------------------------------------------------------------
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7      
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |    M1 |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B

       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P

       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P
  3    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |   
       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P

       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B
  4    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B

       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P

       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P
  6    | H1    |   |       |   | X   X |   | H2    |   |       |   | X   X |   | H3    |   |       |   
       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

monster move down or attack hero if hero is in range.
monster M1:Natsunomeryu move forward
monster M2:BigBad-Wolf move forward
monster M3:Natsunomeryu move forward
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B
  1    |    M1 |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       P - P - P   P - P - P   I - I - I   C - C - C   B - B - B   I - I - I   B - B - B   B - B - B   

       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   K - K - K   I - I - I   C - C - C   P - P - P   I - I - I   P - P - P   P - P - P

       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P
  3    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   B - B - B   I - I - I   C - C - C   K - K - K   I - I - I   P - P - P   P - P - P

       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B
  4    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |   
       B - B - B   B - B - B   I - I - I   K - K - K   K - K - K   I - I - I   K - K - K   B - B - B

       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       B - B - B   P - P - P   I - I - I   C - C - C   K - K - K   I - I - I   B - B - B   P - P - P

       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P
  6    | H1    |   |       |   | X   X |   | H2    |   |       |   | X   X |   | H3    |   |       |
       C - C - C   C - C - C   I - I - I   P - P - P   P - P - P   I - I - I   K - K - K   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
/////////////// Removed turns to move up to attack position to keep file length down /////////////////
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

<OUTPUT>
monster move down or attack hero if hero is in range.
monster M1:Casper move forward
monster M2:Casper move forward
monster M3:Casper move forward
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |   
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P   

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P
  3    |    M1 |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P
  4    | H1    |   |       |   | X   X |   | H2    |   |       |   | X   X |   | H3    |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P   

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>
attack monster with lowest hp!
H1 [ Gaerdal_Ironhand ] attack M1 [ Casper ] causes damage: 350.0
Monster Casper dead
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50
M3. Casper                100      1       100       100         0.50
---------------------------------------------------------------------------------------------------
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>
attack monster with lowest hp!
The monster dodge your attack!
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50
M3. Casper                100      1       100       100         0.50  
---------------------------------------------------------------------------------------------------
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>
attack monster with lowest hp!
The monster dodge your attack!
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50
M3. Casper                100      1       100       100         0.50  
---------------------------------------------------------------------------------------------------
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |   
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |   
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P
  3    |       |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P
  4    | H1    |   |       |   | X   X |   | H2    |   |       |   | X   X |   | H3    |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P   

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

monster move down or attack hero if hero is in range.
monster M2:Casper attack H2: Rillifane_Rallathil
M2 [ Casper ] attack H2 [ Rillifane_Rallathil ] caused damage 100.0
Hero Rillifane_Rallathil is dead
hero List
name                       HP     level    mana      strength          dexterity         agility       money      exp
---------------------------------------------------------------------------------------------------
H1. Gaerdal_Ironhand      100      1      100       700          500         600       854      7
H3. Parzival              100      1      300       750          650         700       2000     9
---------------------------------------------------------------------------------------------------
monster M3:Casper attack H3: Parzival
hero M3:Parzival had dodged
hero List
name                       HP     level    mana      strength          dexterity         agility       money      exp
---------------------------------------------------------------------------------------------------
H1. Gaerdal_Ironhand      100      1      100       700          500         600       854      7    
H3. Parzival              110      1      330       750          650         700       2100     14   
---------------------------------------------------------------------------------------------------
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K   

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P
  3    |       |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |    M3 |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P
  4    | H1    |   |       |   | X   X |   |       |   |       |   | X   X |   | H3    |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
  7    |       |   |       |   | X   X |   | H2    |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>
attack monster with lowest hp!
H3 [ Parzival ] attack M3 [ Casper ] causes damage: 375.0
Monster Casper dead
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50
---------------------------------------------------------------------------------------------------
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible  
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K   
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P   
  3    | H1    |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P   
  4    |       |   |       |   | X   X |   |       |   |       |   | X   X |   | H3    |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P
  6    |       |   |       |   | X   X |   | H2    |   |       |   | X   X |   |       |   |       |   
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

monster move down or attack hero if hero is in range.
monster M2:Casper move forward
5 rounds away, there are 3 new monsters coming!
ready to fight!
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50  
M4. Natsunomeryu          100      1       100       200         0.10
M5. Blinky                100      1       450       350         0.35  
M6. BigBad-Wolf           100      1       150       250         0.15
---------------------------------------------------------------------------------------------------
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |    M4 |   |       |   | X   X |   |    M5 |   |       |   | X   X |   |    M6 |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K   

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P   
  3    | H1    |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P
  4    |       |   |       |   | X   X |   |    M2 |   |       |   | X   X |   | H3    |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P
  6    |       |   |       |   | X   X |   | H2    |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
s
<OUTPUT>
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
t
<OUTPUT>
 which cell you want to teleport to ? 
enter the row you want to teleport:
<INPUT>
5
<OUTPUT>
enter the col you want to teleport: 
<INPUT>
4
<OUTPUT>
 teleport successfully! 
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7      
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  0    |    M4 |   |       |   | X   X |   |    M5 |   |       |   | X   X |   |    M6 |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K
  1    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P   
  2    | H1    |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P
  3    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P   
  4    |       |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   | H3    |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P   

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   | H2    |   |       |   | X   X |   |       |   |       |   
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

monster move down or attack hero if hero is in range.
monster M2:Casper attack H3: Parzival
hero M2:Parzival had dodged
Hero:  Parzival level up!
Parzivalcurrent level:  2
hero List
name                       HP     level    mana      strength          dexterity         agility       money      exp
---------------------------------------------------------------------------------------------------
H1. Gaerdal_Ironhand      100      1      100       700          500         600       854      7    
H2. Rillifane_Rallathil   50       1      650       750          450         500       2000     9    
H3. Parzival              110      2      399       825          715         735       2200     29
---------------------------------------------------------------------------------------------------
monster M4:Natsunomeryu move forward
monster M5:Blinky move forward
monster M6:BigBad-Wolf move forward
                                                  map information
 cell information ?
N-Nexus: home of heroes and monsters
I-Impassible: cell that is inaccessible
C-Cave: increase 10% of agility of any hero in it
B-Bush: increase 10% of dexterity of any hero in it
K-Koulou: increase 10% of strength of any hero in it

 Col       0           1           2           3           4           5           6           7
 Row   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   
  0    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K   
  1    |    M4 |   |       |   | X   X |   |    M5 |   |       |   | X   X |   |    M6 |   |       |   
       K - K - K   K - K - K   I - I - I   P - P - P   C - C - C   I - I - I   C - C - C   K - K - K

       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P
  2    | H1    |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       C - C - C   P - P - P   I - I - I   K - K - K   P - P - P   I - I - I   K - K - K   P - P - P   

       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P
  3    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P
  4    |       |   |       |   | X   X |   |    M2 |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   P - P - P   B - B - B   I - I - I   P - P - P   P - P - P

       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P
  5    |       |   |       |   | X   X |   |       |   | H3    |   | X   X |   |       |   |       |
       P - P - P   P - P - P   I - I - I   P - P - P   K - K - K   I - I - I   C - C - C   P - P - P

       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P   
  6    |       |   |       |   | X   X |   |       |   |       |   | X   X |   |       |   |       |
       P - P - P   C - C - C   I - I - I   K - K - K   K - K - K   I - I - I   P - P - P   P - P - P

       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N
  7    |       |   |       |   | X   X |   | H2    |   |       |   | X   X |   |       |   |       |
       N - N - N   N - N - N   I - I - I   N - N - N   N - N - N   I - I - I   N - N - N   N - N - N

H1,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>
attack monster with lowest hp!
H1 [ Gaerdal_Ironhand ] attack M4 [ Natsunomeryu ] causes damage: 350.0
Monster Natsunomeryu dead
 Monsters List:
name                       HP    level      damage        defense         dodge chance
---------------------------------------------------------------------------------------------------
M2. Casper                100      1       100       100         0.50  
M5. Blinky                100      1       450       350         0.35
M6. BigBad-Wolf           100      1       150       250         0.15  
---------------------------------------------------------------------------------------------------
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
v
<OUTPUT>
 welcome to the market!  
1. buy     2. sell     3. finish and end
please select:
<INPUT>
1
<OUTPUT>
welcome to the market, you can buy or sell here
Weapons List
name                   price           level required           base damage          required hands
---------------------------------------------------------------------------------------------------
1. Sword               500            1              800               1
2. Bow                 300            2              500               2    
3. Scythe              1000           6              1100              2    
4. Axe                 550            5              850               1
5. TSwords             1400           8              1600              2    
6. Dagger              200            1              250               1    
---------------------------------------------------------------------------------------------------
Armors List
name                      price         level required             damage reduced
---------------------------------------------------------------------------------------------------
7. Platinum_Shield       150            1               200           
8. Breastplate           350            3               600
9. Full_Body_Armor       1000           8               1100
10. Wizard_Shield        1200           10              1500
11. Guardian_Angel       1000           10              1000
---------------------------------------------------------------------------------------------------
Potions List
name                     price          level required            attribute gain           affect attribute
---------------------------------------------------------------------------------------------------
12. Healing_Potion       250            1                100             Health   
13. Strength_Potion      200            1                75              Strength
14. Magic_Potion         350            2                100             Mana     
15. Luck_Elixir          500            4                65              Agility  
16. Mermaid_Tears        850            5                100             Health|Mana|Strength|Agility
17. Ambrosia             1000           8                150             All Health|Mana|Strength|Dexterity|Defense|Agility
---------------------------------------------------------------------------------------------------
Spells List
name                    price         level required          base damage        mana cost      type
---------------------------------------------------------------------------------------------------
18. Snow_Cannon          500            2             650            250       Ice
19. Ice_Blade            250            1             450            100       Ice
20. Frost_Blizzard       750            5             850            350       Ice
21. Arctic_Storm         700            6             800            300       Ice
22. Flame_Tornado        700            4             850            300       Fire
23. Breath_of_Fire       350            1             450            100       Fire
24. Heat_Wave            450            2             600            150       Fire
25. Lava_Comet           800            7             1000           550       Fire
26. Hell_Storm           600            3             950            600       Fire
27. Lightning_Dagger     400            1             500            150       Lightning       
28. Thunder_Blast        750            4             950            400       Lightning
29. Electric_Arrows      550            5             650            200       Lightning       
30. Spark_Needles        500            2             600            200       Lightning
---------------------------------------------------------------------------------------------------
<INPUT>
1
<OUTPUT>
purchase successfully!
 inventory of Rillifane_Rallathil
name                  price          required level           type
---------------------------------------------------------------------------------------------------
1. Sword               500            1            Weapon  
2. Sword               500            1            Weapon
---------------------------------------------------------------------------------------------------
H2,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
w
<OUTPUT>
H3,what action will you take:
-------------------operation--------------------------
W/w:up; A/a:left; S/s:down; D/d:right
Q/q:quit; I/i:hero's information; O/o:change weapon;
P/p:change armor; U/u:use potion; F/f:attack;
C/c:use spell; T/t:teleport; B/b:back to Nexus;
V/v:market; M/m:show inventory;
please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]:
<INPUT>
f
<OUTPUT>

<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
////////////// Removed turns to move up to winning position to keep file length down /////////////////
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

hero first reaches monster's nexus?Hero wins!
Do you want to play another game! Please enter y/n to choose
n
see you next time
Bye