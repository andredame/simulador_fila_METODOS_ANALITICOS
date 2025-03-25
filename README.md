Para fins de validação, além do seu código-fonte, você também deve entregar o resultado da simulação das seguintes filas:

G/G/1/5, chegadas entre 2...5, atendimento entre 3...5;
G/G/2/5, chegadas entre 2...5, atendimento entre 3…5.

Para ambas simulações, considere inicialmente a fila vazia e o primeiro cliente chegando no tempo 2,0. Realize a simulação com 100.000 aleatórios, ou seja, ao se utilizar o 100.000 aleatório, sua simulação deve se encerrar e a distribuição de probabilidades, bem como os tempos acumulados para os estados da fila devem ser reportados. Além disso, indique o número de perda de clientes (caso tenha havido perda) e o tempo global da simulação.



Estado da fila = ultimo numero

G G A K
G/G/1/3

### CHEGADA 
contabiliza tempo
Se Fila < 3 (k)
    fila++
    se fila <=1 (A)
        agenda saida(t + numRandom(numSaida))
agenda chegada ( t + numrandom(numchegada))

### Saida 
contabiliza tempo 
Fila --
se file >=1 (A)
    agenda saida(t + numRandom(numSaida))


### contabiliza tempo = Tempo atual - tempo anterior 
