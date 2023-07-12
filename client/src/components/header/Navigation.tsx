
import styled from "styled-components"
import constants from "../../common/constants"
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

    @media(max-width: ${constants.ui.maxWidth}) {
        flex-direction: row;
    }
`

const Menu = styled.div`
    display: flex;
    flex: 1;
    gap: 1rem;
    flex-direction: column;
    
    @media(max-width: ${constants.ui.maxWidth}) {
        flex-direction: row-reverse;
        justify-content: flex-start;
        align-items: flex-end;
    }
`

const Navigation = () => {
    
    return (
        <Header>
            <Navbar>
                <h1>ELEVATOR</h1> 
                <Menu>
                    <Button variant="contained" component={Link} to="/new">
                        New
                    </Button>
                    <Button variant="outlined" component={Link} to="/">
                        SESSIONS
                    </Button>
                </Menu>
            </Navbar>
        </Header>
    )
}

export default Navigation