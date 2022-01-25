export class Commits {
  constructor(commits) {
    this.commits = commits;
    this.observers = [];
  }

  subscribe(observer) {
    this.observers.push(observer);
  }

  notify(commits) {
    this.observers.forEach((observer) => observer.update(commits || this.commits));
  }

  setCommits(commits) {
    this.commits = commits;
    this.notify();
  }

  sortByAuthorName() {
    
    const commits = this.commits;
    commits.sort((a, b) => {
        const nameA = a.author.name.toUpperCase(); // ignore upper and lowercase
        const nameB = b.author.name.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
          return -1;
        }
        if (nameA > nameB) {
          return 1;
        }
      
        return 0;
    })

    this.notify(commits);
  }

  sortByCreatedOn(direction) {
      const commits = this.commits;
      commits.sort((a, b) => {
        const dateA =  new Date(a.createdOn);
        const dateB =  new Date(b.createdOn);
        if(direction === 0) return dateB- dateA ;
        else return dateA - dateB;
    
    })
    this.notify(commits);
  }

  search(keyword) {
    const keywordLowerCase = keyword.toLowerCase();
    const commits = this.commits.filter((commit) => {        
        return commit.message.toLowerCase().includes(keywordLowerCase) 
        || commit.author.name.toLowerCase().includes(keywordLowerCase)
        || commit.author.email.toLowerCase().includes(keywordLowerCase)
    });
    this.notify(commits);
  }
}
