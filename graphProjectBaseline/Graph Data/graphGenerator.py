import numpy as np
import math
import random

fileSizes = [10,20,30,50,100,1000,2500]
#fileSizes = [100]

directional = True
density = 0.02

for graph_size in fileSizes:

    Matrix = [[0 for x in range(graph_size+1)] for y in range(graph_size+1)] 
    
    Matrix[0][0] = 'X' 

    for i in range(1,graph_size+1):
        Matrix[0][i] = 'n' + str(i)
        Matrix[i][0] = 'n' + str(i)

    for i in range(1,graph_size):
        
        connections = math.ceil(graph_size*density)
        connections = max(1, connections)
        for j in range(0,connections):
            
            node = random.randrange(1, graph_size+1)
            while(node == i):
                node = random.randrange(1, graph_size+1)

            weight =  random.randrange(1, 9)
            Matrix[i][node] = weight
            Matrix[node][i] = weight

    if directional:
        for i in range(2,graph_size+1):
            for j in range(1, i):
                Matrix[i][j] = 0
    
     
    #for i in range(0, graph_size):
    #    for j in range(0,graph_size):
    #        print(Matrix[i][j], end=" ")
    #    print("")
    

    filename = f"{graph_size}x{graph_size}Graph.txt"

    with open(filename, 'w') as file:
        for row in Matrix:
            row_string = ' '.join(map(str, row)) 
            file.write(row_string + '\n')
