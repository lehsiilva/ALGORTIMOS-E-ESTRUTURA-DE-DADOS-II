#include <stdio.h>

int main() {
    int N;

    while(scanf("%d", &N) == 1 && N != 0) {
        int h, m, c;
        int criticos = 0;
        int sala_livre = 0; // minutos desde 7:00

        for(int i = 0; i < N; i++) {
            scanf("%d %d %d", &h, &m, &c);

            int chegada = (h - 7) * 60 + m;

            // calcula o próximo horário de atendimento disponível (slot de 30 min)
            int inicio_atendimento;
            if(chegada >= sala_livre) {
                // se chegou depois da sala ficar livre, espera pelo próximo slot
                if(chegada % 30 == 0) {
                    inicio_atendimento = chegada;
                } else {
                    inicio_atendimento = chegada + (30 - chegada % 30);
                }
            } else {
                // se chegou antes da sala ficar livre, espera pelo próximo slot após sala_livre
                if(sala_livre % 30 == 0) {
                    inicio_atendimento = sala_livre;
                } else {
                    inicio_atendimento = sala_livre + (30 - sala_livre % 30);
                }
            }

            int espera = inicio_atendimento - chegada;
            if(espera > c)
                criticos++;

            sala_livre = inicio_atendimento + 30; // próxima vez que a sala estará livre
        }

        printf("%d\n", criticos);
    }

    return 0;
}
