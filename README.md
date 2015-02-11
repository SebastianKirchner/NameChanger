# NameChanger
Small Java Program that allows you to change the names of a large amount of files at once, making them uniform with a counter (zero padded).

How it works:
  * run program in command line
  * you will be prompted to input a path to the directory containing files
  * you have four choices:
      0 => changes the names of all the files
      1 => changes all files that have a certain String in their name
      2 => delete only one part of the name
      3 => Quit the program

After you made your choice you will be prompted to input the new name (0, 1), an identifier (1) or the part that should be deleted from all filenames (2). 

Directory Search is crash-safe => you will be prompted to input a path as long as you input an invalid path or as long as the path you give contains no files. 

New names can include white_space, but should not.
