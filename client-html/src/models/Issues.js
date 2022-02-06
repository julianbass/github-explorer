import { Subject } from "/src/models/Subject.js";

export class Issues extends Subject {
    constructor(issues) {
        super(issues)

    }

    setIssues(issues) {
        this.state = issues;
        this.notify();
    }

    sortByAuthorName() {
    
        const issues = this.state;
        issues.sort((a, b) => {
            const nameA = a.user.toUpperCase(); // ignore upper and lowercase
            const nameB = b.user.toUpperCase(); // ignore upper and lowercase
            if (nameA < nameB) {
              return -1;
            }
            if (nameA > nameB) {
              return 1;
            }
          
            return 0;
        })
    
        this.notify(issues);
      }
    
      sortByCreatedOn(direction) {
          const issues = this.state;
          issues.sort((a, b) => {
            const dateA =  new Date(a.createdAt);
            const dateB =  new Date(b.createdAt);
            if(direction === 0) return dateB- dateA ;
            else return dateA - dateB;
        
        })
        this.notify(commits);
      }
    
      search(keyword) {
        const keywordLowerCase = keyword.toLowerCase();
        const commits = this.state.filter((issue) => {        
            return (issue.body && issue.body.toLowerCase().includes(keywordLowerCase)) 
            || issue.user.toLowerCase().includes(keywordLowerCase)
            || issue.title.toLowerCase().includes(keywordLowerCase)
        });
        this.notify(commits);
      }
}