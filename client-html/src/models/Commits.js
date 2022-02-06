import { Subject } from "/src/models/Subject.js";

export class Commits extends Subject {


  constructor(commits) {
    super(commits);
    
  }
  

  setCommits(commits) {
    this.state = commits;
    this.notify(commits);
  }

 
  sortByAuthorName() {
    
    const commits = this.state;
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
      const commits = this.state;
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
    const commits = this.state.filter((commit) => {        
        return commit.message.toLowerCase().includes(keywordLowerCase) 
        || commit.author.name.toLowerCase().includes(keywordLowerCase)
        || commit.author.email.toLowerCase().includes(keywordLowerCase)
    });
    this.notify(commits);
  }
}
