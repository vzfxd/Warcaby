package pl.warcaby.Checkers.Variations;

import pl.warcaby.Checkers.*;

import java.util.ArrayList;

public class PolishBoard extends Board {

    public PolishBoard(Player whitePlayer, Player blackPlayer){
        this.width = 10;
        this.heigth = 10;
        this.fields = new Field[10][10];
        for(int y = 0; y<10;y++){
            for(int x = 0; x<10;x++){
                if(y<4 && (x+y)%2==0) {this.fields[x][y] = new Field(new Pawn(whitePlayer));}
                else if(y>5 && (x+y)%2==0){this.fields[x][y] = new Field(new Pawn(blackPlayer));}
                else{this.fields[x][y] = new Field();}
            }
        }
        this.turn = Color.WHITE;
    }
    @Override
    public ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation) {
        ArrayList<int[]> bestMoves =  new ArrayList<>();
        int x = pawnLocation[0];
        int y = pawnLocation[1];
        int bestMoveLength = 0;
        bestMoves.add(new int[]{0,0});
        bestMoves.add(pawnLocation);
        if(this.fields[x][y].getPawnColor()==this.turn){
            ArrayList<ArrayList<int[]>> allMoves = getAllMoves(pawnLocation);
            if(allMoves==null){
                return null;
            }
            for (ArrayList<int[]> move : allMoves) {
                int len = move.get(0)[0];
                if (len > bestMoveLength) {
                    bestMoves = new ArrayList<>();
                    int lim = len + 2;
                    if(lim<3){lim=3;}
                    for (int j = 0; j < lim; j++) {bestMoves.add(move.get(j));}
                    bestMoveLength = len;
                } else if (len == bestMoveLength) {
                    int lim = len + 2;
                    if (lim<3) {lim = 3;}
                    for (int j = 2; j < lim; j++) {bestMoves.add(move.get(j));}
                }
            }
            if(bestMoves.size()==2){
                return null;
            }
            return bestMoves;

        }
        else return null;
    }

    private ArrayList<int[]> addLastQueenMove(int[] pawnlocation, int newx, int newy){
        ArrayList<int[]> move = new ArrayList<>();
        move.add(new int[]{0,0});
        move.add(pawnlocation);
        move.add(new int[]{newx, newy});
        return move;
    }


    @Override
    protected ArrayList<ArrayList<int[]>> getAllMoves(int[] pawnLocation) {
        int x = pawnLocation[0];
        int y = pawnLocation[1];
        if(this.fields[x][y].getOccupied()==null){
            return null;
        }
        Color pawnColor = this.fields[x][y].getPawnColor();
        PawnType pawnType = this.fields[x][y].getPawnType();
        ArrayList<ArrayList<int[]>> allMoves = new ArrayList<>();
        Color enemyPawnColor;
        if(pawnColor==Color.BLACK){enemyPawnColor=Color.WHITE;}
        else{enemyPawnColor=Color.BLACK;}

        //Zwykle pionki

        if(pawnType==PawnType.NORMAL){

            //Biale pionki

            if(pawnColor==Color.WHITE){

                //ruch w lewo

                if(x>0 && y<9 && this.fields[x-1][y+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x-1,y+1});
                    allMoves.add(move);
                }

                //ruch w prawo
                if(x<7 && y<9 && this.fields[x+1][y+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x+1,y+1});
                    allMoves.add(move);
                }
            }

            //Czarne pionki

            else {

                //ruch w lewo

                if (x > 0 && y > 0 && this.fields[x - 1][y - 1].getPawnColor() == null) {
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x, y});
                    move.add(new int[]{x - 1, y - 1});
                    allMoves.add(move);
                }

                //ruch w prawo
                if (x < 9 && y > 0 && this.fields[x + 1][y - 1].getPawnColor() == null) {
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{0, 0});
                    move.add(new int[]{x, y});
                    move.add(new int[]{x + 1, y - 1});
                    allMoves.add(move);
                }
            }

            //bicie pionki zwykle
            //bicie w lewo gora

            if(x>1 && y<8 && this.fields[x-1][y+1].getPawnColor()==enemyPawnColor && this.fields[x-2][y+2].getPawnColor()==null){
                this.fields[x-2][y+2].setOccupied(this.fields[x][y].getOccupied());
                ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x-2,y+2});
                int moreways = 0;
                for(ArrayList<int[]> mv : fullMove){
                    if(mv.get(0)[0]!=0){
                        moreways++;
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                        move.add(new int[]{x,y});
                        for(int i=1; i<mv.size();i++){move.add(mv.get(i));}
                        allMoves.add(move);
                    }
                }
                //koniec sciezki bicia
                if(moreways==0){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{1, 1});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x-2,y+2});
                    allMoves.add(move);
                }
                this.fields[x-2][y+2].setOccupied(null);
            }

            //bicie w prawo gora

            if(x<8 && y<8 && this.fields[x+1][y+1].getPawnColor()==enemyPawnColor && this.fields[x+2][y+2].getPawnColor()==null){
                this.fields[x+2][y+2].setOccupied(this.fields[x][y].getOccupied());
                ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x+2,y+2});
                int moreways = 0;
                for(ArrayList<int[]> mv : fullMove){
                    if(mv.get(0)[0]!=0){
                        moreways++;
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                        move.add(new int[]{x,y});
                        for(int i=1; i<mv.size();i++){move.add(mv.get(i));}
                        allMoves.add(move);
                    }
                }
                //koniec sciezki bicia
                if(moreways==0){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{1, 1});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x+2,y+2});
                    allMoves.add(move);
                }
                this.fields[x+2][y+2].setOccupied(null);
            }

            //bicie w lewo dol

            if(x>1 && y>1 && this.fields[x-1][y-1].getPawnColor()==enemyPawnColor && this.fields[x-2][y-2].getPawnColor()==null){
                this.fields[x-2][y-2].setOccupied(this.fields[x][y].getOccupied());
                ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x-2,y-2});
                int moreways = 0;
                for(ArrayList<int[]> mv : fullMove){
                    if(mv.get(0)[0]!=0){
                        moreways++;
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                        move.add(new int[]{x,y});
                        for(int i=1; i<mv.size();i++){move.add(mv.get(i));}
                        allMoves.add(move);
                    }
                }
                //koniec sciezki bicia
                if(moreways==0){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{1, 1});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x-2,y-2});
                    allMoves.add(move);
                }
                this.fields[x-2][y-2].setOccupied(null);
            }

            //bicie w prawo dol

            if(x<8 && y>1 && this.fields[x+1][y-1].getPawnColor()==enemyPawnColor && this.fields[x+2][y-2].getPawnColor()==null){
                this.fields[x+2][y-2].setOccupied(this.fields[x][y].getOccupied());
                ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{x+2,y-2});
                int moreways = 0;
                for(ArrayList<int[]> mv : fullMove){
                    if(mv.get(0)[0]!=0){
                        moreways++;
                        ArrayList<int[]> move = new ArrayList<>();
                        move.add(new int[]{mv.get(0)[0]+1,mv.get(0)[0]+1});
                        move.add(new int[]{x,y});
                        for(int i=1; i<mv.size();i++){move.add(mv.get(i));}
                        allMoves.add(move);
                    }
                }
                //koniec sciezki bicia
                if(moreways==0){
                    ArrayList<int[]> move = new ArrayList<>();
                    move.add(new int[]{1, 1});
                    move.add(new int[]{x,y});
                    move.add(new int[]{x+2,y-2});
                    allMoves.add(move);
                }
                this.fields[x+2][y-2].setOccupied(null);
            }
        }



        //Pionki typu QUEEN

        else if(pawnType==PawnType.QUEEN){
            //szukanie bicia gora lewo
            int nx = x-1;
            int ny = y+1;
            while(nx>0 && ny<9){
                if(this.fields[nx][ny].getPawnColor()==pawnColor){break;}
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    int moreways = 0;
                    this.fields[nx-1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                    this.fields[nx+1][ny-1].setOccupied(this.fields[x][y].getOccupied()); //blokuje ruch w ta sama strone
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx-1,ny+1});
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv!=null && mv.get(0)[0]>0){
                            moreways++;
                            move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                            move.add(pawnLocation);
                            for(int i = 1; i<mv.size();i++){move.add(mv.get(i));}
                            allMoves.add(move);
                        }
                    }
                    if(moreways==0){
                        move.add(new int[]{1,1});
                        move.add(pawnLocation);
                        move.add(new int[]{nx-1,ny+1});
                        allMoves.add(move);
                    }
                    this.fields[nx-1][ny+1].setOccupied(null);
                    if(nx+1!=x && ny-1!=y) {
                        this.fields[nx + 1][ny - 1].setOccupied(null);
                    }
                    break;
                }
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny+1].getPawnColor()==enemyPawnColor){
                    break;
                }
                else{
                    ArrayList<int[]> move = addLastQueenMove(pawnLocation,nx,ny);
                    allMoves.add(move);
                    nx--;
                    ny++;
                    if(ny==9 || nx==0){
                        if(this.fields[nx][ny].getPawnColor()==null){
                            move = addLastQueenMove(pawnLocation, nx ,ny);
                            allMoves.add(move);
                        }
                    }
                }
            }

            //szukanie bicia dol lewo

            nx = x-1;
            ny = y-1;
            while(nx>0 && ny>0){
                if(this.fields[nx][ny].getPawnColor()==pawnColor){break;}
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny-1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    int moreways = 0;
                    this.fields[nx-1][ny-1].setOccupied(this.fields[x][y].getOccupied());
                    this.fields[nx+1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx-1,ny-1});
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv!=null && mv.get(0)[0]>0){
                            moreways++;
                            move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                            move.add(pawnLocation);
                            for(int i = 1; i<mv.size();i++){move.add(mv.get(i));}
                            allMoves.add(move);
                        }
                    }
                    if(moreways==0){
                        move.add(new int[]{1,1});
                        move.add(pawnLocation);
                        move.add(new int[]{nx-1,ny-1});
                        allMoves.add(move);
                    }
                    this.fields[nx-1][ny-1].setOccupied(null);
                    if(nx+1!=x && ny+1!=y) {
                        this.fields[nx + 1][ny + 1].setOccupied(null);
                    }
                    break;
                }
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx-1][ny-1].getPawnColor()==enemyPawnColor){
                    break;
                }
                else{
                    ArrayList<int[]> move = addLastQueenMove(pawnLocation,nx,ny);
                    allMoves.add(move);
                    nx--;
                    ny--;
                    if(ny==0 || nx==0){
                        if(this.fields[nx][ny].getPawnColor()==null){
                            move = addLastQueenMove(pawnLocation, nx ,ny);
                            allMoves.add(move);
                        }
                    }
                }
            }

            //szukanie bicia gora prawo

            nx = x+1;
            ny = y+1;
            while(nx<9 && ny<9){
                if(this.fields[nx][ny].getPawnColor()==pawnColor){break;}
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny+1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    int moreways = 0;
                    this.fields[nx+1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                    this.fields[nx-1][ny-1].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx+1,ny+1});
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv!=null && mv.get(0)[0]>0){
                            moreways++;
                            move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                            move.add(pawnLocation);
                            for(int i = 1; i<mv.size();i++){move.add(mv.get(i));}
                            allMoves.add(move);
                        }
                    }
                    if(moreways==0){
                        move.add(new int[]{1,1});
                        move.add(pawnLocation);
                        move.add(new int[]{nx+1,ny+1});
                        allMoves.add(move);
                    }
                    this.fields[nx+1][ny+1].setOccupied(null);
                    if(nx-1!=x && ny-1!=y) {
                        this.fields[nx - 1][ny - 1].setOccupied(null);
                    }
                    break;
                }
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny+1].getPawnColor()==enemyPawnColor){
                    break;
                }
                else{
                    ArrayList<int[]> move = addLastQueenMove(pawnLocation,nx,ny);
                    allMoves.add(move);
                    nx++;
                    ny++;
                    if(ny==9 || nx==9){
                        if(this.fields[nx][ny].getPawnColor()==null){
                            move = addLastQueenMove(pawnLocation, nx ,ny);
                            allMoves.add(move);
                        }
                    }
                }
            }

            //szukanie bicia dol prawo

            nx = x+1;
            ny = y-1;
            while(nx<9 && ny>0){
                if(this.fields[nx][ny].getPawnColor()==pawnColor){break;}
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny-1].getPawnColor()==null){
                    ArrayList<int[]> move = new ArrayList<>();
                    int moreways = 0;
                    this.fields[nx+1][ny-1].setOccupied(this.fields[x][y].getOccupied());
                    this.fields[nx-1][ny+1].setOccupied(this.fields[x][y].getOccupied());
                    ArrayList<ArrayList<int[]>> fullMove = getAllMoves(new int[]{nx+1,ny-1});
                    for(ArrayList<int[]> mv : fullMove){
                        if(mv!=null && mv.get(0)[0]>0){
                            moreways++;
                            move.add(new int[]{mv.get(0)[0]+1, mv.get(0)[0]+1});
                            move.add(pawnLocation);
                            for(int i = 1; i<mv.size();i++){move.add(mv.get(i));}
                            allMoves.add(move);
                        }
                    }
                    if(moreways==0){
                        move.add(new int[]{1,1});
                        move.add(pawnLocation);
                        move.add(new int[]{nx+1,ny-1});
                        allMoves.add(move);
                    }
                    this.fields[nx+1][ny-1].setOccupied(null);
                    if(nx-1!=x && ny+1!=y) {
                        this.fields[nx - 1][ny + 1].setOccupied(null);
                    }
                    break;
                }
                if(this.fields[nx][ny].getPawnColor()==enemyPawnColor && this.fields[nx+1][ny-1].getPawnColor()==enemyPawnColor){
                    break;
                }
                else{
                    ArrayList<int[]> move = addLastQueenMove(pawnLocation,nx,ny);
                    allMoves.add(move);
                    nx++;
                    ny--;
                    if(ny==0 || nx==9){
                        if(this.fields[nx][ny].getPawnColor()==null){
                            move = addLastQueenMove(pawnLocation, nx ,ny);
                            allMoves.add(move);
                        }
                    }
                }
            }
        }
        return allMoves;
    }


    @Override
    public void move(ArrayList<int[]> steps) {
        Pawn pawn = this.fields[steps.get(0)[0]][steps.get(0)[1]].getOccupied();
        this.fields[steps.get(0)[0]][steps.get(0)[1]].setOccupied(null);
        this.fields[steps.get(1)[0]][steps.get(1)[1]].setOccupied(pawn);
        int x1 = steps.get(0)[0];
        int y1 = steps.get(0)[1];
        int x2 = steps.get(1)[0];
        int y2 = steps.get(1)[1];
        if(y2==7 || y2==0){
            this.fields[x2][y2].pawnTypeUpgrade();
        }
        if(x1<x2 && y1>y2){this.fields[x2-1][y2+1].setOccupied(null);}                 //zbicie/ruch od gornej lewej
        else if(x1<x2 && y1<y2){this.fields[x2-1][y2-1].setOccupied(null);}            //zbicie/ruch od dolnej lewej
        else if(x1>x2 && y1>y2){this.fields[x2+1][y2+1].setOccupied(null);}            //zbicie/ruch od gornej prawej
        else if(x1>x2 && y1<y2){this.fields[x2+1][y2-1].setOccupied(null);}            //zbicie/ruch od dolnej prawe
    }
}
