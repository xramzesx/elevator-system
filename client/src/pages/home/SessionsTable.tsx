import { SimulationConfig } from '@common/types';
import { 
    Table, 
    TableHead, 
    TableRow, 
    TableCell, 
    TableBody, 
    Paper
} from '@mui/material';

import { useMemo} from 'react';
import SessionItem from './SessionItem';

interface ISessionsTable {
    configs: Map<string, SimulationConfig>
}

const SessionsTable = (props: ISessionsTable) => {

    const sessions = useMemo(() => {
        let index = 0
        const result : any[] = []
        props.configs.forEach(
            (config, simulationId) => result.push(
                <SessionItem
                    config={config}
                    simulationId={simulationId}
                    index={index++}
                    key={simulationId}
                />
            )
        )
        return result
    }, [props.configs])

    return (
        <Paper elevation={2}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell/>
                        <TableCell>NO</TableCell>
                        <TableCell>Elevators</TableCell>
                        <TableCell>System</TableCell>
                        <TableCell>Factory</TableCell>
                        <TableCell>Strategy</TableCell>
                        <TableCell>Link</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {sessions}
                </TableBody>
            </Table>
        </Paper>
    ) 
}

export default SessionsTable