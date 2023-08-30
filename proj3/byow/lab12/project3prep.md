# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer:
The way I implemented placing the hexagons was to allow coordinates to be entered for the starting location of each hexagon. This way, it was easy working through the placings. The downside, however, was that I needed to manually figure out the starting position of each hexagon in the tessellation rather than relying on the coordinates of the previous hexagon. The lesson to learn is that there is a tradeoff between ease of implementation and automation, and this depends on my design choice.
-----

**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer: 
The analogy would be the hexagons being the rooms and hallways and the tesselation being the world. The process of generating world is more difficult because it is less structured.

-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer:
I would write a method for generating rooms and hallways first. I would try to see if it is possible to make it random and then work on generating the world.
-----
**What distinguishes a hallway from a room? How are they similar?**

Answer:
A hallway is just two intersecting rooms. Both involve enclosing space between blocks but a hallway has a turn.
