export class Issues {
    constructor(Issues) {
        this.issues = Issues || [];
        this.observers = [];
    }

    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach((ob) => ob.update(this.issues));
    }

    setIssues(issues) {
        this.issues = issues;
        this.notify();
    }
}