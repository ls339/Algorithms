# example input
#input = [45,45,45,45,45,65,45,45,45,66]
input = [1,2,3,1,2,3,1,2,3]

output = [256]
s = []
# Intialize table
table = [[x] for x in range(0,258)]

for x in input:    
    n = [input[0]]
    input = input[1:] 
    if s + n in table :
        s = s + n
    else :
        output.append(table.index(s))
        table.append(s + n)
        s = list(n)
output.append(table.index(s))
output.append(257)
print(output)
