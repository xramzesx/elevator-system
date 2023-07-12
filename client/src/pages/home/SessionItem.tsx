import { SimulationConfig } from "@common/types"
import { 
    Box, 
    Button, 
    Collapse, 
    IconButton, 
    List, 
    ListItem, 
    ListItemText, 
    TableCell, 
    TableRow, 
    Typography 
} from "@mui/material"
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp'
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown'
import { useState } from "react"
import { Link } from 'react-router-dom'

interface ISessionItem {
    config: SimulationConfig,
    simulationId: string,
    index: number,
}

const SessionItem = (props: ISessionItem) => {
    const [isOpen, setIsOpen] = useState(false)
    
    const handleOpen = () => {
        setIsOpen(!isOpen)
    }
    return (
        <>
            <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
                <TableCell>
                    <IconButton onClick={handleOpen}>
                        {isOpen ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>    
                </TableCell>
                <TableCell>{props.index}</TableCell>
                <TableCell>{props.config.elevatorCount}</TableCell>
                <TableCell>{props.config.systemVariant}</TableCell>
                <TableCell>{props.config.factoryVariant}</TableCell>
                <TableCell>{props.config.strategyVariant}</TableCell>
                <TableCell>
                    <Button variant="contained"  component={Link} to={`/simulation/${props.simulationId}`}>
                        Open
                    </Button>   
                </TableCell>
            </TableRow>
            <TableRow>
                <TableCell colSpan={7} style={{ paddingBottom: 0, paddingTop: 0 }}>
                    <Box style={{display: "flex", alignItems: "center", justifyContent: "center"}}>
                        <Collapse in={isOpen} timeout="auto" unmountOnExit>
                            <Typography variant="h5" gutterBottom component="div" style={{textAlign: "center", paddingTop: "1rem"}}>
                                Details
                            </Typography>

                            <List style={{display: "flex", flexDirection: "row", width: "100%", gap: "1rem"}}>
                                <ListItem>
                                    <ListItemText primary="Highest" secondary={props.config.highestFloor}/>
                                </ListItem>
                                <ListItem>
                                    <ListItemText primary="Lowest" secondary={props.config.lowestFloor}/>
                                </ListItem>
                                <ListItem>
                                    <ListItemText primary="Delay" secondary={props.config.delay}/>
                                </ListItem>
                            </List>
                        </Collapse>                        
                    </Box>
                </TableCell>
            </TableRow>
        </>
    )
}


export default SessionItem