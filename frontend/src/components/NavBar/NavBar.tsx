import {Link} from 'react-router-dom';

export default function NavBar() {
  return (
    
    <nav className="navbar navbar-light navbar-expand-lg " style = {{backgroundColor : "#e3f2fd", paddingLeft:"2rem"}}>
      <span className="navbar-brand mb-0 h1">
        Library
      </span>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li>
            <Link to={"/books"} className="nav-link"> List books</Link>
          </li>
        </ul>
      </div>
    </nav>
    
  );
}
