export class Repo {
    constructor(repo) {
        this.repo = repo;
        this.observers = [];
    }

    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach((ob) => ob.update(this.repo));
    }

    getName() {
        return this.repo.name;
    }

    getOwner() {
        return this.repo.owner;
    }

    setRepo(repo) {
        this.repo = repo;
        this.notify();
    }
}