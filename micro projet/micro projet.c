#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <fcntl.h>
#include <errno.h>

#define NUM_CHILDREN 4
#define SEM_NAME "/sync_sem"

pid_t children[NUM_CHILDREN];
sem_t *sem;

void handle_signal(int sig);
void child_process(int id);

void handle_signal(int sig) {
    if (sig == SIGUSR1) {
        printf("Parent received confirmation from a child.\n");
    }
}


void child_process(int id) {
    sem_wait(sem);

    printf("Child %d starting task.\n", id);
    sleep(2);
    for (volatile int i = 0; i < 100000000; i++); 

    kill(getppid(), SIGUSR1);

    printf("Child %d finished task.\n", id);
    exit(0);
}

int main() {
   
    sem = sem_open(SEM_NAME, O_CREAT, 0644, 0);
    if (sem == SEM_FAILED) {
        perror("sem_open failed");
        exit(EXIT_FAILURE);
    }

    struct sigaction sa;
    sa.sa_handler = handle_signal;
    sa.sa_flags = 0;
    sigemptyset(&sa.sa_mask);

    if (sigaction(SIGUSR1, &sa, NULL) == -1) {
        perror("sigaction failed");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < NUM_CHILDREN; i++) {
        pid_t pid = fork();
        if (pid == -1) {
            perror("fork failed");
            exit(EXIT_FAILURE);
        }
        if (pid == 0) {
            child_process(i + 1);
        } else {
            children[i] = pid;
        }
    }

    sleep(1); 
    for (int i = 0; i < NUM_CHILDREN; i++) {
        sem_post(sem);
    }

    for (int i = 0; i < NUM_CHILDREN; i++) {
        waitpid(children[i], NULL, 0);
    }

    sem_close(sem);
    sem_unlink(SEM_NAME);

    printf("All child processes have completed.\n");

    return 0;
}