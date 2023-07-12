
import styled from "styled-components"
import constants from "../../common/constants"
import SystemForm from "./SystemForm"
import { Button } from "@mui/material"
import { Link } from "react-router-dom"

const Header = styled.header`
    background-color: ${constants.ui.colors.background};
    color: ${constants.ui.colors.foreground};
    /* color: white; */
    display: flex;
    align-items: flex-start;
    justify-content: center;
    min-height: 7rem;
    position: relative;
`

const Navbar = styled.nav`
    padding: 1rem 2rem;
    width: 100%;
    max-width: ${constants.ui.maxWidth};
    display: flex;
    flex-direction: column;
    justify-content: flex-end;

    gap: 1rem;

    position: sticky;
    top: 0;
`

const Navigation = () => {
    
    return (
        <Header>
            <Navbar>
                <h1>ELEVATOR</h1> 
                <Button variant="contained" component={Link} to="/new">
                    New
                </Button>
                <Button variant="contained" component={Link} to="/">
                    SESSIONS
                </Button>
                <SystemForm />               
            </Navbar>
        </Header>
    )
}

export default Navigation