export class Commits {
    constructor(commits) {
        this.commits = commits;
        this.observers = [];
    }

    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach((observer) => observer.update(this.commits));
    }

    updateCommits(commits) {
        this.commits = commits;
        this.notify();
    }

}