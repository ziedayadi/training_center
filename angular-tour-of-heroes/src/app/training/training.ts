import {Session} from './session';

export class Training {
  id: number;
  name: string;
  description:string;
  reference:string;
  prerequisites:string;
  period:number;
  sessions:Session[];

}
